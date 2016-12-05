#!/usr/bin/env ruby
# encoding: utf-8

require 'rubygems'

HERE = File.expand_path( '..',__FILE__ )
APP_NAME = "app"

task :default => ["build"]

task :debug do
 #TODO: use for debugging
end

desc "Run circle ci commands"
task :circle do
  circle_job_number = ENV["CIRCLE_BUILD_NUM"]
  puts "VERSIONING CIRCLE BUILD NUMBER #{circle_job_number}"
  clean_environment
  assemble("release")
  #Since we only run this task on develop or master, we need not check if
  # the branch is develop or master
  puts "branch is #{get_branch_name}, updating version and deploying."
  update_version_and_deploy
end

task :prep_build do
  clean_environment
  run_tests_and_checks
end

desc "Build & Test"
task :build do
  clean_environment
  run_tests_and_checks
  assemble("release")
end

desc "Run lint"
task :lint do
  run_lint
end

desc "Assemble release"
task :assemble do
  assemble("release")
end

desc "Clean"
task :clean do
  clean_environment
end

def update_version_and_deploy
  Versioning.increment_current_version_name
  new_version_name = Versioning.get_current_version_name
  puts "Current version is : #{new_version_name}"

  run_shell "#{gradle} clean assemble testDebug lintDebug"
end

def assemble(target)
  run_shell("#{gradle} assemble#{capitalize(target)}")
end

def capitalize(word)
  word[0].capitalize + word[1..word.length]
end

def get_branch_name
  branch = ENV["CIRCLE_BRANCH"]
  branch
end

def clean_environment
  run_shell("#{gradle} clean")
end

def run_tests_and_checks
  run_shell "#{gradle} clean lintDebug testDebug"
end

def run_unit_test
  run_shell "#{gradle} testDebug connectedCheck"
end

def run_lint
  run_shell "#{gradle} lintDebug"
end

def gradle
  gradle = (ENV['CI_BUILD'].nil?) ? "./gradlew" : "gradle"
end

def run_shell(command)
  sh "#{command}"
end

def run_shell_with_output(command)
  `#{command}`
end

def construct_app_path(build_type)
  if build_type == "release" || build_type == "staging"
    "#{HERE}/#{APP_NAME}/build/outputs/apk/#{APP_NAME}-#{build_type}.apk"
  else
    "#{HERE}/#{APP_NAME}/build/outputs/apk/#{APP_NAME}-#{build_type}-unaligned.apk"
  end
end

def proguard_mapping_file(build_type)
  "#{HERE}/#{APP_NAME}/build/outputs/mapping/#{build_type}/mapping.txt"
end

def copy_with_path(src, dst)
  FileUtils.mkdir_p(File.dirname(dst))
  FileUtils.cp(src, dst)
end

def valid_json?(json)
  begin
    JSON.parse(json)
    return true
  rescue Exception => e
    return false
  end
end

#!/usr/bin/env python

import fire
import os

class Cli():

    def __init__(self):
        self.java = Java()


class Java():
    def install(self):
        #fix maven wrapper
        self._run('mvn -N io.takari:maven:wrapper')
        self._run('mvn install')

    def compile(self):
        os.system('cd java;  mvn compile')

    def run(self):
        os.system('cd java; ./mvnw spring-boot:run')

    def run_jar(self):
        os.system('cd java; java -jar target/resilience_talk-0.0.1-SNAPSHOT.jar')


    def _run(self, cmd):
        return os.system(f'cd java ; {cmd}')

if __name__ == '__main__':
    fire.Fire(Cli)


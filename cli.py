import fire
import os

class Cli():

    def __init__(self):
        self.java = Java()


class Java():
    def install(self):
        os.system('cd java;  mvn install')

    def compile(self):
        os.system('cd java;  mvn compile')

    def run(self):
        os.system('cd java; java -jar target/resilience_talk-0.0.1-SNAPSHOT.jar')

if __name__ == '__main__':
    fire.Fire(Cli)


class Animal(object):
    def __init__(self, name, sound):
        self.name = name
        self.sound = sound

if __name__ == "__main__":
    animals = [
        Animal("cat", "Cat goes fiddle-i-fee."),
        Animal("hen", "Hen goes chimmy-chuck, chimmy-chuck,"), 
        Animal("duck", "Duck goes quack, quack,"), 
        Animal("goose", "Goose goes hissy, hissy,"), 
        Animal("sheep", "Sheep goes baa, baa,"), 
        Animal(f"{{other}}", f"{{other}} goes lorem ipsum,")
    ]


    for i, animal in enumerate(animals):
        print("Got me a " + animal.name + " and the " + animal.name + " pleased me,")
        print("I fed my " + animal.name + " under yonder tree.")
        for previous_animal in animals[:i + 1][::-1]:
            print(previous_animal.sound)
        print()

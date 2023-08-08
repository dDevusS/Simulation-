# Simulation
Симуляция жизни дикого мира на языке Java.

![ddd1](https://github.com/dDevusS/Simulation-/assets/140493120/f6b85675-aee0-4d9e-aa0d-035a457ff74f) ![Screenshot 2023-08-07 143705](https://github.com/dDevusS/Simulation-/assets/140493120/49773d66-a8a6-4beb-b7c4-4f33979f9df7)

### About
В данный момент в Симуляции реализованы весь необходимый функционал для соответсвия ТЗ.
- возможно создать мир размером от 10х10 до 50х50. Все нежевые обьекты и существа будут добавлены автоматически.
- симуляцию мира возможно поставить на паузу в любой момент.
- во время паузы симуляции возможно добавить в мир различных существ и траву.
- трава имеет 3 степени роста. При 3 степени трава будет разрастаться по миру давая новые побеги на соседние пустые клетки.
- в целях баланса реализовано автоматическое добавление травы при ее недостаточном количесве в мире.
- деревья время от времени будут давать свои плоды.
- животные деляться на травоядных и хищников. У них есть уровень здоровья, атаки, голода, возраст.
  Голодные животные будут терять свое здоровье. Если здоровье животных достигнет 0 или возраст дойдет до определенных значений они погибнут и оставят после себя куски мяса.
  Хищники, в отсутвии поблизости падали, будут нападать на травоядных. Здоровье хищника так же может уменьшится при неудачной охоте.
  Если сущесва сыты и довольны жизнью они буду двать приплод от 1 до 3 особей в зависимости от вида.
  У существ разная скорость с которой они передвигаются по миру и с ним взаимодействуют(у хищников 3, 2 у травоядных).

### In future
В дальнейшем в проекте хотелось бы реализовать следующие вещи:
- пол животных.
- новые виды животны, вплоть до очень экзотических.
- улучшенное поведение животных.
- графический интерфейс.
- и многое другое.

### CUI
Отображение мира и взаимодейсвие с ним реализовано через консоль.

#### В этом проекте реализуется в соответсвии с техническим заданием из данного курса https://zhukovsd.github.io/java-backend-learning-course/Projects/Simulation/

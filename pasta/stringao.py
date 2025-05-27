import random
import string

# Gerar uma string aleatória de 100.000 caracteres alfanuméricos
length = 60_000_000
characters = string.ascii_letters + string.digits
random_string = ''.join(random.choices(characters, k=length))

# Salvar em um arquivo de texto
with open("string_aleatoria2_60000000.txt", "w") as file:
    file.write(random_string)
import random
import string

# Gerar uma string aleatória de 100.000 caracteres alfanuméricos
length = 600_000
characters = string.ascii_letters + string.digits
random_string = ''.join(random.choices('A', k=length))

# Salvar em um arquivo de texto
with open("string600.txt", "w") as file:
    file.write(random_string)
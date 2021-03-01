# Read Me First
Weather forecast application for a far away solar system.

# Considerações
- As órbitas de todos os planetas são circulares.
- O tamanho dos planetas e do Sol foi desconsiderado.
- O período total considerado foi de 3600 dias, o equivalente a 10 anos no planeta Ferengi, 30 anos no planeta Betasoide e 50 anos em Vulcano, o planeta mais rápido entre os três.
- O período de tempo correspondente a 1 dia é o mesmo nos 3 planetas - Vulcano, Ferengi e Betasoide.
- A condição de alinhamento - colinearidade - entre os 3 planetas foi obtida através do cálculo da área do triângulo formado entre os mesmos. Idealmente o alinhamento ocorre quando a área de tal triângulo é zero, porém nos cálculos realizados por esta API está sendo considerado como zero até uma relativamente pequena áera de 30.000 Km2, ou seja, planetas alinhados. Isso porque as demais áreas são 10 e até 100 vezes maiores.
 
# Simulador de Algoritmos de Substituição de Páginas

Este projeto é uma ferramenta educacional desenvolvida em Java para simular e comparar o desempenho de diferentes algoritmos de substituição de páginas utilizados em sistemas de gerenciamento de memória virtual.

## 🚀 Como o Projeto Funciona

O simulador avalia quantas "faltas de página" (page faults) ocorrem em uma sequência de acessos à memória para um número determinado de quadros (frames) disponíveis. 

### Algoritmos Implementados:

1.  **FIFO (First-In, First-Out):** A página que entrou há mais tempo na memória é a primeira a ser removida.
2.  **LRU (Least Recently Used):** Substitui a página que não é utilizada há mais tempo, assumindo que páginas usadas recentemente têm maior probabilidade de serem usadas de novo.
3.  **Clock (Relógio):** Uma variação eficiente do FIFO que dá uma "segunda chance" para páginas que foram referenciadas recentemente.
4.  **NFU (Not Frequently Used):** Substitui a página que possui a menor frequência de uso acumulada.

## ⚙️ Fluxo de Execução da Simulação

Para garantir clareza sobre como o software processa os dados, aqui está o passo a passo da execução interna:

1.  **Entrada de Dados:** O usuário fornece uma sequência de números (IDs das páginas) e o tamanho da memória física (quantidade de quadros).
2.  **Processamento em Lote:** Ao clicar em "Executar", o sistema percorre a lista de algoritmos (FIFO, LRU, Clock, NFU).
3.  **Simulação Independente:** Cada algoritmo recebe a **mesma** sequência de páginas e o **mesmo** número de quadros. Eles operam de forma isolada para garantir uma comparação justa.
4.  **Cálculo de Faltas:** Durante o processamento de cada página na sequência:
    *   O sistema verifica se a página já está nos "quadros" (memória).
    *   Se **não estiver**, ocorre uma **Falta de Página (Page Fault)**. O algoritmo então decide qual página deve ser removida para dar lugar à nova, baseando-se em sua regra específica (ex: a mais antiga no FIFO).
5.  **Coleta de Resultados:** O número total de faltas de cada algoritmo é encapsulado em um objeto de resultado.
6.  **Atualização Visual:** A interface gráfica recebe esses resultados e atualiza simultaneamente a tabela detalhada e o gráfico de barras.

## 📋 Pré-requisitos

Para executar este projeto, você precisará ter instalado em sua máquina:
*   **Java JDK (versão 8 ou superior)**
*   Um terminal ou prompt de comando

## 🛠️ Passo a Passo para Execução

Siga as instruções abaixo para compilar e rodar o simulador:

### 1. Preparar o ambiente
Abra o seu terminal e navegue até a pasta raiz do projeto (`SimuladorPaginas`).

### 2. Compilar o projeto
Execute o comando abaixo para compilar todos os arquivos Java:
```bash
javac -d out src/model/*.java src/algorithms/*.java src/gui/*.java src/Main.java
```
*Isso criará uma pasta `out` com os arquivos compilados.*

### 3. Executar o simulador
Após a compilação bem-sucedida, execute o programa com o seguinte comando:
```bash
java -cp out Main
```

## 🖥️ Como Usar a Interface

1.  **Sequência de Páginas:** No campo de texto, insira os números das páginas separados por vírgula (ex: `1,2,3,4,1,2,5,1,2,3`).
2.  **Quadros:** Insira a quantidade de memória disponível (ex: `3`).
3.  **Executar:** Clique no botão "Executar Simulação".
4.  **Análise:** 
    *   O painel à esquerda mostrará a tabela com o número exato de faltas de página para cada algoritmo.
    *   O gráfico central exibirá uma comparação visual rápida do desempenho.
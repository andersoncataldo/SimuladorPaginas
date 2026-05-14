# Simulador de Algoritmos de Substituição de Páginas

Este projeto é uma ferramenta educacional avançada desenvolvida em Java para simular e visualizar detalhadamente o comportamento de diferentes algoritmos de substituição de páginas em sistemas de memória virtual.

## 🚀 Novidades da Versão Atual

O projeto foi recentemente atualizado para oferecer uma análise muito mais profunda do que apenas a contagem de faltas:
*   **Rastreamento Passo a Passo:** Agora você pode ver o estado exato da memória (quadros) após cada requisição de página.
*   **Interface Moderna:** Interface Swing totalmente remodelada com abas, tabelas interativas e gráficos coloridos.
*   **Visualização de Faltas:** Destaque visual em tempo real para cada ocorrência de "Page Fault".

## 🧠 Algoritmos Implementados

O simulador avalia o desempenho e a lógica de substituição dos seguintes algoritmos:

1.  **FIFO (First-In, First-Out):** A página que entrou há mais tempo na memória é a primeira a ser substituída. Simples, mas sujeito à *Anomalia de Belady*.
2.  **LRU (Least Recently Used):** Substitui a página que não é utilizada há mais tempo. É um dos algoritmos mais eficientes na prática.
3.  **Clock (Relógio):** Uma implementação eficiente que simula o LRU usando um "bit de referência" e um ponteiro circular, dando uma "segunda chance" às páginas acessadas recentemente.
4.  **NFU (Not Frequently Used):** Baseia-se na frequência de uso. Substitui a página que foi acessada menos vezes ao longo do tempo.

## ⚙️ Fluxo de Execução e Lógica Interna

Para garantir total fidelidade aos conceitos de Sistemas Operacionais:

1.  **Processamento Isolado:** Cada algoritmo processa a sequência de forma totalmente independente, garantindo que o estado de um não afete o outro.
2.  **Histórico Completo:** Ao contrário de simuladores básicos, este projeto registra:
    *   A página solicitada.
    *   O conteúdo de cada quadro de memória naquele instante.
    *   Se houve ou não uma "Falta de Página".
3.  **Detalhamento Visual:** Os dados coletados são enviados para a interface gráfica, que organiza as informações em tabelas dinâmicas onde cada linha representa um passo da simulação.

## 🖥️ Como Usar a Interface

1.  **Configuração:** Insira a sequência de páginas (ex: `1,2,3,4,1,2,5`) e a quantidade de quadros (ex: `3`) no painel superior.
2.  **Execução:** Clique em **"Simular"**.
3.  **Navegação por Abas:**
    *   **Dashboard Comparativo:** Veja um gráfico de barras moderno comparando o total de faltas de cada algoritmo.
    *   **Rastreamento Detalhado:** Explore as abas individuais de cada algoritmo para ver a tabela passo a passo. Páginas que causaram falta são destacadas com **"SIM" em vermelho**.

## 🛠️ Instruções para Execução

### Pré-requisitos
*   **Java JDK (versão 8 ou superior)** instalado.

### Compilação e Execução (Via Terminal)

1.  Navegue até a pasta raiz do projeto:
    ```bash
    cd SimuladorPaginas
    ```

2.  Compile o projeto:
    ```bash
    javac -d out -sourcepath src src/Main.java
    ```

3.  Execute o simulador:
    ```bash
    java -cp out Main
    ```

---
*Este simulador foi desenvolvido para fins didáticos, permitindo observar fenômenos como a Anomalia de Belady e as diferenças estratégicas entre substituição por antiguidade, recência e frequência.*

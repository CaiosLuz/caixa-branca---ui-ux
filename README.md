# 🔍 Análise de Código – Teste de Caixa Branca

Este repositório reúne a análise completa do método `verificarUsuario()` da classe `User`, realizada como parte da atividade de Teste de Caixa Branca.  
O objetivo é avaliar a estrutura interna do código, identificar seus fluxos de execução e aplicar técnicas formais de análise.

A atividade foi desenvolvida seguindo as etapas solicitadas:

1. Revisão do código-fonte  
2. Construção do grafo de fluxo  
3. Cálculo da complexidade ciclomática  
4. Identificação dos caminhos básicos  
5. Documentação e organização do código  
6. Execução de testes e registro dos resultados  

Todas essas etapas estão detalhadas nas seções abaixo.

---

# 🧩 1. Revisão do Código-Fonte

<img width="859" height="424" alt="image" src="https://github.com/user-attachments/assets/641b2a1d-2fcf-4604-aa15-2b53dc191108" />

O arquivo da planilha com as respostas se encontra neste repositório na pasta docs `/doc/teste-caixa-branca.xlsx`.

---

# 🧭 2.Notação de Grafo de Fluxo

O grafo foi montado com 11 nós, representando cada bloco lógico do método analisado.

<img width="300" height="400" alt="grafo drawio" src="https://github.com/user-attachments/assets/0784cec6-7bbb-451f-9600-dd3c97483d63" />

> A imagem do grafo está no repositório em `/doc/grafo_fluxo.png`.

### ✔ Lista dos nós:

| Nº | Descrição |
|----|-----------|
| (1) | Início do método |
| (2) | Montagem da query SQL |
| (3) | Tentativa de conexão |
| (4) | Decisão: conexão é nula? |
| (5) | Retorno imediato (false) |
| (6) | Execução do Statement / Query |
| (7) | Decisão: `rs.next()` ? |
| (8) | Usuário encontrado |
| (9) | Catch interno (erro na query) |
| (10) | Catch externo (erro ao conectar) |
| (11) | Retorno final |

**Total de nós: `N = 11`**

# 📐 3. Arestas do Grafo

A partir do grafo foram identificadas 14 transições entre nós:

**Total de arestas: `E = 14`**

---

# 🎯 4. Cálculo da Complexidade Ciclomática (McCabe)

A fórmula utilizada:

M = E − N + 2P

Onde:

- `E = 14` (arestas)
- `N = 11` (nós)
- `P = 1` (componente conectado)

Substituindo:

M = 14 − 11 + 2×1
M = 5

### ✔ Complexidade Ciclomática Final:
M = 5

Isso significa que o método possui **5 caminhos independentes**, exigindo ao menos 5 testes para cobertura total.

---

# 🛣️ 5. Caminhos Básicos

Abaixo estão os cinco caminhos independentes:

| Caminho | Descrição | Sequência |
|---------|-----------|-----------|
| **C1** | Conexão nula (retorno imediato) | `1 → 2 → 3 → 4 → 5 → 11` |
| **C2** | Usuário encontrado | `1 → 2 → 3 → 4 → 6 → 7 → 8 → 11` |
| **C3** | Usuário NÃO encontrado | `1 → 2 → 3 → 4 → 6 → 7 → 11` |
| **C4** | Erro interno na execução da query | `1 → 2 → 3 → 4 → 6 → 9 → 11` |
| **C5** | Erro externo ao conectar | `1 → 2 → 3 → 10 → 11` |

Cada um desses caminhos deve ser testado para cobertura completa.

---

## 🔍 Resultados da Execução (Console)

A seguir, alguns prints de execução dos testes realizados no Eclipse:

<img width="408" height="88" alt="image" src="https://github.com/user-attachments/assets/42cae563-fb02-4e9c-9a0e-e1c74dca00e7" />

<img width="390" height="123" alt="image" src="https://github.com/user-attachments/assets/63d2158e-034d-488f-912c-da9c1b95ae1b" />

<img width="419" height="121" alt="image" src="https://github.com/user-attachments/assets/9903d1d9-7a50-46ed-a170-aad70419ab27" />


---

## 🔍 Conclusão

A análise de caixa branca realizada permitiu compreender de forma clara a estrutura e o funcionamento interno do código. 
A partir do grafo de fluxo, do cálculo da complexidade ciclomática e da definição dos caminhos básicos, foi possível identificar todos os fluxos de execução e avaliar a qualidade do método analisado.
O processo também contribuiu para melhorar a documentação, legibilidade e organização do código, além de reforçar sua confiabilidade por meio dos testes executados. 
Assim, o trabalho atinge seu objetivo ao aplicar técnicas de análise estrutural que aprimoram a compreensão e a manutenção do software.


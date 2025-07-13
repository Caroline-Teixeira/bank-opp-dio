
# 🏦 Sistema Bancário DIO — Java (Console + Maven)

![Feito com Java](https://img.shields.io/badge/Feito%20com-Java-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-Build%20Tool-important?style=for-the-badge&logo=apachemaven)
![Console App](https://img.shields.io/badge/Console-Java%2021-blueviolet?style=for-the-badge)

<a href="https://github.com/Caroline-Teixeira/bank-opp-dio/blob/main/README_ENG.md"><img src="https://raw.githubusercontent.com/yammadev/flag-icons/refs/heads/master/png/US%402x.png" alt="Inglês" ></a>

## 📖 Descrição

Sistema bancário simulado via terminal, desenvolvido em **Java 21**, utilizando conceitos de **Programação Orientada a Objetos (POO)**, **tratamento de exceções personalizadas**, **polimorfismo** e **Maven** para gerenciamento de dependências.

O projeto permite o cadastro de clientes, abertura de contas, operações financeiras como depósito, saque, transferência, investimentos simulados com rendimento diário, e histórico de transações.

## 🎯 Funcionalidades

- ✅ Cadastro de clientes com validação de CPF
- ✅ Criação de contas corrente vinculadas a clientes
- ✅ Depósito, saque e transferência entre contas
- ✅ Registro e histórico de transações
- ✅ Simulação de investimentos (CDB, LCI, Tesouro Direto e Fundo Imobiliário)
- ✅ Histórico de investimentos
- ✅ Tratamento de erros customizado
- ✅ Menu interativo via console
- ✅ Organização modular por pacotes

## 📂 Estrutura do Projeto

```
.
├── pom.xml
└── src/
    └── main/
        └── java/
            └── br/
                └── com/
                    └── bank_dio/
                        ├── exception/
                        ├── model/
                        ├── repository/
                        ├── util/
                        └── view/
```

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Maven 3.9.6**
- **Tratamento de Exceções Personalizadas**
- **Programação Orientada a Objetos (POO)**

## 📖 Conceitos Aplicados

### 📌 Herança e Polimorfismo

O projeto utiliza herança e polimorfismo para diferenciar tipos de conta:

```java
public abstract class Account { ... }

public class CheckingAccount extends Account { ... }

public class SavingsAccount extends Account { ... }
```

Isso permite que o sistema trate as contas de forma genérica onde possível, mas mantenha comportamentos específicos para cada tipo via sobrescrita de métodos ou extensões futuras.

---

### 📌 Tratamento de Erros Personalizado

Foram criadas exceções customizadas para tratar erros específicos:

```java
public class BankException extends RuntimeException { ... }

public class InvalidTransaction extends BankException { ... }
public class DuplicateClient extends BankException { ... }
```

Isso garante mensagens de erro claras, e controle preciso sobre as falhas do sistema.

---

### 📌 Estrutura em Camadas (MVC-like)

O projeto está organizado de forma modular:

- `model/` → Entidades (Client, Account, Transaction)
- `repository/` → Controle de armazenamento (simulação de banco de dados via lista)
- `view/` → Interface com o usuário via console (menus)
- `exception/` → Exceções personalizadas
- `util/` → Utilitários de validação e formatação

---

## 📖 Como Executar

### 🖥️ Via IDE

- Execute a classe `Main.java`
- Navegue pelos menus no console.

### 🖥️ Via Terminal

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="br.com.bank_dio.Main"
```

### 📦 Pré-requisitos:

- Java 21 ou superior
- Maven configurado no PATH

---

## 📌 Sugestões de Melhoria

- 📊 Criar relatórios de rendimento acumulado dos investimentos
- 💾 Persistir dados via arquivo ou banco de dados
- 🖥️ Criar interface gráfica (Swing ou JavaFX)
- 🖥️ Refatoração e aprimoramento das classes existentes
- 📑 Testes unitários com JUnit

---

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE).

## 👩‍💻 Autor(a)

[Caroline 💙](https://github.com/Caroline-Teixeira)

---

📌 *Projeto desenvolvido para o desafio da DIO (Digital Innovation One)*


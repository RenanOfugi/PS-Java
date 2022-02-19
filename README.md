# Avaliação Java

## Objetivo

Este projeto visa atender os requisitos do desafio proposto pela Supera Tecnologia

## Descrição

Projeto de API de um pseudo ecommerce de games mobile utilizando Spring, em que é possível ter múltiplos carrinhos e cada um podendo ter múltiplos produtos. 

## Requisitos

| Requisitos                                                   | Status | Endpoint                                                     | Método Requisição |
| ------------------------------------------------------------ | ------ | ------------------------------------------------------------ | ----------------- |
| Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente | Feito  | **/api/v1/store/cart/{id do carrinho}** ou **/api/v1/store/cart** para ver todos os carrinhos | **GET**           |
| O usuário poderá adicionar produtos do carrinho              | Feito  | **/api/v1/store/cart/{id do carrinho}/products/{id do produto para adicionar}/add** | **POST**          |
| O usuário poderá remover produtos do carrinho                | Feito  | **/api/v1/store/cart/{id do carrinho}/products/{id do produto para adicionar}/remove** | **DELETE**        |
| O usuário poderá ordenar os produtos por preço, popularidade (score) e ordem alfabética. | Feito  | **/api/v1/store/cart/{id do carrinho}/sort/{atributo}/{tipo de ordenação}** - Atributos: **name**, **price** ou **score**; Tipo de ordenação permitidos: **asc** ou **desc** | **GET**           |
| A cada produto adicionado, deve-se somar R$ 10,00 ao frete.  | Feito  | Este requisito é atendido ao adicionar ou remover um produto ao carrinho - pode ser verificado juntamente com o requisito "**Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente**" | -                 |
| Quando o valor dos produtos adicionados ao carrinho for igual ou superior a R$ 250,00, o frete é grátis. | Feito  | Este requisito é atendido ao adicionar ou remover um produto ao carrinho - pode ser verificado juntamente com o requisito "**Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente**" | -                 |

# Detalhamento Técnico

## Tecnologias e Justificativas

Este projeto foi implementado usando:

- **Spring Boot**: Devido ao meu maior conhecimento e pelo tempo de entrega do desafio, optei por essa ferramenta pela facilidade de configuração, me permitindo focar mais nas regras de negócio do projeto.
- **HSQLDB**: Como o projeto inicial do desafio já utilizava este banco de dados em memória, optei por mantê-la. Banco de dados em memória tem a desvantagem de não manter a persistência dos dados, "zerando" tudo sempre que ela é inicializada, mas devido à restrição de tempo e por ser utilizada somente para este desafio, mantive a utilização do HSQLDB.
- **Java 11**: Versão Java já configurada na máquina
- **Maven**: Por ser o gerenciador de dependências clássico do Spring Boot
- **Lombok**: Visa diminuir o código, gerando métodos como getters, setters, construtores e o padrão Builder, por exemplo, utilizando somente anotações.

## Dados populados no Banco de Dados

Todos os dados de produtos são populados no HSQLDB a partir do arquivo **data.sql** localizado em **src/main/resources/data.sql**. Portanto, se for preciso modificar os dados iniciais e/ou adicionar mais produtos, é necessário apenas inserir novas linhas neste arquivo.


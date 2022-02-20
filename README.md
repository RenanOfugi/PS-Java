# Avaliação Java

## Objetivo

Este projeto visa atender os requisitos do desafio proposto pela Supera Tecnologia.

## Descrição

Projeto de API de um pseudo ecommerce de games mobile utilizando Spring, em que é possível ter múltiplos carrinhos e cada um podendo ter múltiplos produtos. 

## Requisitos

| Requisitos                                                   | Status | Endpoint                                                     | Método Requisição |
| ------------------------------------------------------------ | ------ | ------------------------------------------------------------ | ----------------- |
| Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente | Feito  | **/api/v1/store/cart/{id do carrinho}** ou **/api/v1/store/cart** para ver todos os carrinhos. | **GET**           |
| O usuário poderá adicionar produtos do carrinho              | Feito  | **/api/v1/store/cart/{id do carrinho}/products/{id do produto para adicionar}/add** | **POST**          |
| O usuário poderá remover produtos do carrinho                | Feito  | **/api/v1/store/cart/{id do carrinho}/products/{id do produto para adicionar}/remove** | **DELETE**        |
| O usuário poderá ordenar os produtos por preço, popularidade (score) e ordem alfabética. | Feito  | **/api/v1/store/cart/{id do carrinho}/sort/{atributo}/{tipo de ordenação}** - Atributos: **name**, **price** ou **score**; Tipo de ordenação permitidos: **asc** ou **desc** | **GET**           |
| A cada produto adicionado, deve-se somar R$ 10,00 ao frete.  | Feito  | Este requisito é atendido ao adicionar ou remover um produto ao carrinho - pode ser verificado juntamente com o requisito "**Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente**". | -                 |
| Quando o valor dos produtos adicionados ao carrinho for igual ou superior a R$ 250,00, o frete é grátis. | Feito  | Este requisito é atendido ao adicionar ou remover um produto ao carrinho - pode ser verificado juntamente com o requisito "**Os valores exibidos no checkout (frete, subtotal e total) devem ser calculados dinamicamente**". | -                 |

# Detalhamento Técnico

## Tecnologias e Justificativas

Este projeto foi implementado usando:

- **Spring Boot**: Em razão ao curto tempo de entrega do desafio, optei por essa ferramenta pela facilidade de configuração, me permitindo focar mais nas regras de negócio do projeto.
- **HSQLDB**: Como o projeto inicial do desafio já utilizava este banco de dados em memória, optei por mantê-la. Banco de dados em memória tem a desvantagem de não manter a persistência dos dados, "zerando" tudo sempre que ela é inicializada, mas devido à restrição de tempo e por ser utilizada somente para este desafio, mantive a utilização do HSQLDB.
- **Java 11**: Versão Java já configurada na máquina.
- **Maven**: Por ser o gerenciador de dependências clássico do Spring Boot.
- **Lombok**: Visa diminuir o código, gerando métodos como getters, setters, construtores e o padrão Builder, por exemplo, utilizando somente anotações.

## Dados populados no Banco de Dados

Todos os dados de produtos são populados no HSQLDB a partir do arquivo **data.sql** localizado em **src/main/resources/data.sql**. Portanto, se for preciso modificar os dados iniciais e/ou adicionar mais produtos, é necessário apenas modificar ou inserir novas linhas neste arquivo.

## Observações - alterações na classe Product fornecida

Como foi necessário modificar a classe Product, fornecido pela empresa, vale ressaltar as seguintes modificações:

- Foram alterados os modificadores de acesso iniciais da classe Product, de **public** para **private**, para manter o encapsulamento (um dos pilares da programação orientada a objetos).
- Foram também adicionados anotações de validação e persistência como @NotBlank, @Column, @Table.
- Foram adicionados anotações do Lombok, para reduzir código e aumentar a produtividade, gerando getters, setters e construtor com @Getter, @Setter e @RequiredArgsConstructor
- Foram criados os métodos equals e hashcode para uso das Collections.

## Como executar o projeto

Em um terminal, execute:

```sh
./mvnw install
```

e depois execute:

```sh
./mvnw spring-boot:run
```



## Requisição API

Utilize **[Postman](https://www.postman.com/)** ou **[Insomnia](https://insomnia.rest/)** para enviar requisições à API.

## Endpoints

Ao executar o projeto, estes endpoints estarão disponíveis para atender os requisitos do desafio.

### GET /api/v1/store/cart/{id do carrinho}

Retorna os dados de um carrinho específico.

Ex.: **www.localhost:8080/api/v1/store/cart/1** : Irá retornar um Json referente ao carrinho 1.

```json
{
  "id": 1,
  "subtotal": 250.58,
  "total": 250.58,
  "shippingCost": 0.00,
  "products": [
    {
      "id": 1,
      "name": "Garena Free Fire",
      "price": 10.50,
      "score": 10,
      "image": ""
    },
    {
      "id": 9,
      "name": "Candy Crush Saga",
      "price": 233.89,
      "score": 755,
      "image": ""
    },
    {
      "id": 5,
      "name": "Clash Royale",
      "price": 6.19,
      "score": 80,
      "image": ""
    }
  ]
}
```



### GET /api/v1/store/cart

Retorna os dados de todos os carrinhos da loja.

Ex.: **www.localhost:8080/api/v1/store/cart**

```json
[
  {
    "id": 1,
    "subtotal": 250.58,
    "total": 250.58,
    "shippingCost": 0.00,
    "products": [
      {
        "id": 1,
        "name": "Garena Free Fire",
        "price": 10.50,
        "score": 10,
        "image": ""
      },
      {
        "id": 9,
        "name": "Candy Crush Saga",
        "price": 233.89,
        "score": 755,
        "image": ""
      },
      {
        "id": 5,
        "name": "Clash Royale",
        "price": 6.19,
        "score": 80,
        "image": ""
      }
    ]
  },
  {
    "id": 2,
    "subtotal": 345.58,
    "total": 345.58,
    "shippingCost": 0.00,
    "products": [
      {
        "id": 5,
        "name": "Clash Royale",
        "price": 6.19,
        "score": 80,
        "image": ""
      },
      {
        "id": 9,
        "name": "Candy Crush Saga",
        "price": 233.89,
        "score": 755,
        "image": ""
      },
      {
        "id": 3,
        "name": "PUBG Mobile",
        "price": 105.50,
        "score": 90,
        "image": ""
      }
    ]
  },
  {
    "id": 3,
    "subtotal": 2.99,
    "total": 12.99,
    "shippingCost": 10.00,
    "products": [
      {
        "id": 2,
        "name": "Framed",
        "price": 2.99,
        "score": 20,
        "image": ""
      }
    ]
  }
]
```



### POST /api/v1/store/cart/{id do carrinho}/products/{id do produto para adicionar}/add

Adiciona um produto específico em um carrinho. Se o carrinho não existir, é criado um novo carrinho com este produto, retornando a mensagem **200 OK**. E os dados do subtotal, frete e total são atualizados.

Ex.: **www.localhost:8080/api/v1/store/cart/1/products/7/add** - Irá adicionar o produto 7 no carrinho 1 e retornar:

```
200 OK
```

ou retornará um erro caso o produto não exista.



### DELETE /api/v1/store/cart/{id do carrinho}/products/{id do produto para adicionar}/remove

Remove um produto específico em um carrinho, retornando a mensagem **200 OK**.

Ex.: **www.localhost:8080/api/v1/store/cart/1/products/7/remove** - Irá remover o produto 7 no carrinho 1 e retornar:

``` 
200 OK
```

ou retornará um erro caso o produto não exista.



### GET /api/v1/store/cart/{id do carrinho}/sort/{atributo}/{tipo de ordenação}

Ordena os produtos de um carrinho específico de acordo com os parametros:

- **atributo**: ordena os produtos com base em um atributo do produto especificado. Pode-se ordenar com base no nome ("name"), preço("price") ou popularidade ("score").
- **tipo de ordenação**: ordena de forma crescente("asc") ou decrescente("desc").

Ex.: **www.localhost:8080/api/v1/store/cart/1** - estado inicial

```json
{
  "id": 1,
  "subtotal": 437.30,
  "total": 437.30,
  "shippingCost": 0.00,
  "products": [
    {
      "id": 7,
      "name": "Pokémon Go",
      "price": 125.87,
      "score": 500,
      "image": ""
    },
    {
      "id": 6,
      "name": "Hearthstone",
      "price": 74.55,
      "score": 200,
      "image": ""
    },
    {
      "id": 2,
      "name": "Framed",
      "price": 2.99,
      "score": 20,
      "image": ""
    },
    {
      "id": 9,
      "name": "Candy Crush Saga",
      "price": 233.89,
      "score": 755,
      "image": ""
    }
  ]
}
```

**www.localhost:8080/api/v1/store/cart/1/sort/price/desc** - mesma lista anterior ordenado com base no preço dos produtos e de forma decrescente.

```json
{
  "id": 1,
  "subtotal": 437.30,
  "total": 437.30,
  "shippingCost": 0.00,
  "products": [
    {
      "id": 9,
      "name": "Candy Crush Saga",
      "price": 233.89,
      "score": 755,
      "image": ""
    },
    {
      "id": 7,
      "name": "Pokémon Go",
      "price": 125.87,
      "score": 500,
      "image": ""
    },
    {
      "id": 6,
      "name": "Hearthstone",
      "price": 74.55,
      "score": 200,
      "image": ""
    },
    {
      "id": 2,
      "name": "Framed",
      "price": 2.99,
      "score": 20,
      "image": ""
    }
  ]
}
```

## Plus - endpoint

Alguns endpoints que não fazem parte dos requisitos do desafio, mas utilizei para consultar no processo de construção desde projeto.

### GET /api/v1/store/products

Retorna uma lista todos os produtos cadastrados no banco de dados.

### GET /api/v1/store/products/{id}

Retorna um produto específico do banco de dados
# Desafio Spring - MeLi Bootcamp

## Arquivos JSON

Foram utilizados arquivos JSON para armazenar os dados. Esses arquivos podem ser encontrados no seguinte diretório: "src/main/java/com/desafiospring/DesafioSpring/json". São três arquivos: user.json, product.json e post.json, cada um deles já possui conteúdo inserido.

## Postman

No diretório raíz, existe um arquivo com uma coleção de Requests do POSTMAN. O nome do arquivo é: Postman - SOCIAL MELI.json.

## Funcionalidades

Os endpoints da API estão conforme a descrição contida no documento Requisitos Técnicos/Funcionais, disponibilizado para a realização dessa atividade. Apenas duas mudanças foram realizadas. Tipos Date são passados como String no Payload. A outra mudança foi a adição de uma funcionalidade para adicionar um novo usuário (US 0013). 

## US 0001

Realizar a ação de seguir um determinado vendedor.

### Method: Post
### Sign: /users/{userId}/follow/{userIdToFollow}
### Exemple: /users/1/follow/2
### Response: Status Code 200 (tudo OK) | Status Code 400 (Bad Request)

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário atual. |
| userIdToFollow | Inteiro | Número que identifica o usuário a seguir. |

## US 0002

Obter o resultado do número de usuários que seguem um determinado vendedor

### Method: Get
### Sign: /users/{userId}/followers/count/
### Exemple: /users/2/followers/count/
### Response: 

```bash
  {
    "userId": 2,
    "userName": "vendedor2",
    "followers_count": 5
  }
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário. |
| userName | String | Nome de usuário associado ao userId. |

## US 0003

Obter uma lista de todos os usuários que seguem um determinado vendedor

### Method: Get
### Sign: /users/{UserID}/followers/list
### Exemple: /users/2/followers/list
### Response: 

```bash
  {
    "userId": 2,
    "userName": "Usuário 02"
    "followers" : [
                    {
                    "userId":2,
                    "userName":"usuario1"
                    },
                    {
                    "userId":3,
                    "userName":"usuario3"
                    }
                  ]
  }
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário. |
| userName | String | Nome de usuário associado ao userId. |

## US 0004

Obter uma lista de todos os vendedores que um determinado usuário segue.

### Method: Get
### Sign: /users/{UserID}/followed/list
### Exemple: /users/2/followed/list
### Response: 

```bash
{
    "userId": 1,
    "userName": "Usuário 01",
    "followed": [
        {
            "userId": 2,
            "userName": "Usuário 02"
        },
        {
            "userId": 3,
            "userName": "Usuário 03"
        }
    ]
}
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário. |
| userName | String | Nome de usuário associado ao userId. |

## US 0005

Adiciona um novo post.

### Method: Post
### Sign: /products/newpost
### Response: Status Code 200 (tudo OK) | Status Code 400 (Bad Request)
### Payload:

```bash
{
    "userId": 2,
    "id_post" : 7,
    "date" : "06-06-2021",
    "detail" :
        { "product_id" : 7,
        "productName" : "Produto 07",
        "type" : "Comida",
        "brand" : "Marca de Produto 07",
        "color" : "Azul",
        "notes" : "Nota do produto 07"
        },
    "category" : 50,
    "price" : 150.50
}
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica cada usuário. |
| id_post | String | Número de identificação de cada uma das publicações|
| date | String | Data de publicação no formato dd-mm-aaaa. |
| product_id | Inteiro | Número de identificação de cada um dos produtos associados a uma publicação. |
| productName | String | Sequência de caracteres que representa o nome de um produto. |
| type | String | Sequência de caracteres que representa o tipo de um produto |
| brand | String | Sequência de caracteres que representa a marca de um produto. |
| color | String | Sequência de caracteres que representa a cor de um produto. |
| notes | String | Sequência de caracteres para colocar notas ou observações de um produto. |
| category | Inteiro | Identificador usado para saber a categoria à qual um produto pertence. Por exemplo: 100: cadeiras, 58: teclados. |
| price | Double | Preço do produto. |

## US 0006

Obter uma lista das publicações feitas pelos vendedores que um usuário segue
nas últimas duas semanas.

### Method: Get
### Sign: /products/followed/{userId}/list
### Exemple: /products/followed/2/list
### Response: 

```bash
{
    "userId": 1,
    "posts": [
        {
            "id_post": 1,
            "userId": 2,
            "date": "06-06-2021",
            "detail": {
                "product_id": 1,
                "productName": "Produto 01",
                "type": "Comida",
                "brand": "Marca de Produto 01",
                "color": "Azul",
                "notes": "Nota do produto 01"
            },
            "category": 50,
            "price": 150.5,
            "hasPromo": false,
            "discount": 0.0
        }
    ]
}
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário. |

## US 0007

Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um
determinado vendedor.

### Method: Post
### Sign: /users/{userId}/unfollow/{userIdToUnfollow}
### Exemple: /users/1/unfollow/2
### Response: Status Code 200 (tudo OK) | Status Code 400 (Bad Request)

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário atual. |
| userIdToUnfollow | Inteiro | Número que identifica o usuário a deixar de seguir.|

## US 0008

Organiza resposta através de ordem cresce e decrescente.

### Method: Get
### Sign: 
     /users/{UserID}/followers/list?order=name_asc
     /users/{UserID}/followers/list?order=name_desc
     /users/{UserID}/followed/list?order=name_asc
     /users/{UserID}/followed/list?order=name_desc

| Order | Description |
| :---: | :---: |
| name_asc | Alfabético crescente. | 
| name_desc | Alfabético decrescente. |

## US 0009

Classificar por data crescente e decrescente.

### Method: Get
### Sign: 
     /products/followed/{userId}/list?order=date_asc
     /products/followed/{userId}/list?order=date_desc

| Order | Description |
| :---: | :---: |
| date_asc | Data crescente (do mais antigo para o mais novo). | 
| date_desc | Data decrescente (do mais novo ao mais antigo). |

## US 0010

Realiza a publicação de um novo produto promocional.

### Method: Post
### Sign: /products/newpromopost
### Response: Status Code 200 (tudo OK) | Status Code 400 (Bad Request)
### Payload:

```bash
{
    "userId": 2,
    "id_post" : 5,
    "date" : "05-06-2021",
    "detail" :
            { "product_id" : 5,
            "productName" : "Produto 05",
            "type" : "Gamer",
            "brand" : "Marca do Produto 05",
            "color" : "Preto",
            "notes" : "Notas 05"
            },
    "category" : "70",
    "price" : 200.50,
    "hasPromo": true,
    "discount": 0.50
}
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica cada usuário. |
| id_post | String | Número de identificação de cada uma das publicações|
| date | String | Data de publicação no formato dd-mm-aaaa. |
| product_id | Inteiro | Número de identificação de cada um dos produtos associados a uma publicação. |
| productName | String | Sequência de caracteres que representa o nome de um produto. |
| type | String | Sequência de caracteres que representa o tipo de um produto |
| brand | String | Sequência de caracteres que representa a marca de um produto. |
| color | String | Sequência de caracteres que representa a cor de um produto. |
| notes | String | Sequência de caracteres para colocar notas ou observações de um produto. |
| category | Inteiro | Identificador usado para saber a categoria à qual um produto pertence. Por exemplo: 100: cadeiras, 58: teclados. |
| price | Double | Preço do produto. |
| hasPromo | Boolean | Campo true ou false para determinar se um produto está em promoção ou não. |
| discount | Double | Caso um produto esteja em promoção, defina o valor do desconto. |

## US 0011

Obter a quantidade de produtos promocionais de um vendedor específico.

### Method: Get
### Sign: /products/{userId}/countPromo/
### Exemple: /products/2/countPromo/
### Response: 

```bash
{
    "userId": 2,
    "userName": "Usuário 02",
    "promoproducts_count": 1
}
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o usuário. |
| userName | String | Nome de usuário associado ao userId. |
| promoproducts_count | int | Quantidade numérica de produtos em promoção de determinado vendedor. |

## US 0012

Obter uma lista de todos os produtos promocionais de um vendedor específico.

### Method: Get
### Sign: /products/{userId}/list/
### Exemplo: /products/2/list/
### Response:

```bash
{
    "userId": 2,
    "userName": "Usuário 02",
    "posts": [
        {
            "id_post": 5,
            "userId": 2,
            "date": "05-06-2021",
            "detail": {
                "product_id": 5,
                "productName": "Produto 05",
                "type": "Gamer",
                "brand": "Marca do Produto 05",
                "color": "Preto",
                "notes": "Notas 05"
            },
            "category": 70,
            "price": 200.5,
            "hasPromo": true,
            "discount": 0.5
        }
    ]
}
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica cada usuário. |
| userName | String | String que representa o nome do usuário. |
| id_post | String | Número de identificação de cada uma das publicações|
| date | String | Data de publicação no formato dd-mm-aaaa. |
| product_id | Inteiro | Número de identificação de cada um dos produtos associados a uma publicação. |
| productName | String | Sequência de caracteres que representa o nome de um produto. |
| type | String | Sequência de caracteres que representa o tipo de um produto |
| brand | String | Sequência de caracteres que representa a marca de um produto. |
| color | String | Sequência de caracteres que representa a cor de um produto. |
| notes | String | Sequência de caracteres para colocar notas ou observações de um produto. |
| category | Inteiro | Identificador usado para saber a categoria à qual um produto pertence. Por exemplo: 100: cadeiras, 58: teclados. |
| price | Double | Preço do produto. |
| hasPromo | Boolean | Campo true ou false para determinar se um produto está em promoção ou não. |
| discount | Double | Caso um produto esteja em promoção, defina o valor do desconto. |

## US 0013

Adicionar novo usuário.

### Method: Post
### Sign: /users/add
### Response: Status Code 200 (tudo OK) | Status Code 400 (Bad Request)
### Payload:

```bash
  {
      "userId" : "4",
      "userName" : "Usuário 04",
      "seller" : false
  }
```

| Parâmetro | Tipo | Descrição |
| :---: | :---: | :---: |
| userId | Inteiro | Número que identifica o ID do usuário. |
| userName | String | Nome do usuário. |
| seller | Boolean | Indica se um usuário é vendedor ou não. |


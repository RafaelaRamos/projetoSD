# projetoSD
### Questão 01
Execute: 
`sudo sh run.sh`

### Questão 02 - Reconstrua o sistema da questão anterior e faça as devidas mudanças para utilizar gRPC de forma assíncrona e ao final descreva a diferença entre as duas tecnologias para produzir o mesmo resultado.

Há uma diferença notória entre as duas tecnologias o RPC possui suporte a programação estruturada enquanto o RMI segue o paradigma de orientação a objetos. Outra diferença seria com relação a comunicação já que o RPC permite uma comunicação com diferentes linguagens de programação e o RMI está limitado a linguagem Java, em relação ao desempenho e desenvolvimento, pois com o framework gRPC a aplicação realiza a mesma função de forma mais rápida, além de fornecer métodos e uma documentação completa que facilita na implementação de um código mais limpo, legível e eficiente.

### Questão 03 - Analise o código da última questão (2) e avalie o que problemas podem ocorrer e quais soluções podem ser adotadas para resolver o problema e que implicações podem levar esta solução.
- **O servidor cai durante o processamento de uma mensagem**

Neste cenário o cliente pode continuar aguardando até que se tenha uma resposta do servidor ou pode-se definir a quantidade de tentativas que o mesmo deverá efetuar para que se tente obter resposta do servidor.

- **O cliente cai antes de receber uma resposta**

Para que o servidor não fique gastando recursos sem que não haja ninguém para receber a resposta, poderia ser utilizado um armazenamento temporário das mensagens, dessa forma quando o cliente voltasse a resposta da sua requisição seria entregue.

- **Adotar a técnica de recuperação de mensagem _push notification_ ao invés de _pulling_**

Utilizando a técnica de push o cliente iria se registrar em um canal para receber notificações de quando a mensagens solicitadas estivessem prontas, sendo assim a conexão persistente, ao se deparar com problemas estabelecidos nas questões anteriores iria garantir a entrega das mensagens já que elas possuem identificação além do canal de comunicação possuir a identificador do consumidor.

### Equipe
[Amanda Gomes](https://github.com/amdagomes)

[Rafaela Pereira](https://github.com/RafaelaRamos)

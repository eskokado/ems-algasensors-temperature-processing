# Temperature Processing Service

O **Temperature Processing Service** atua no processamento analítico dos dados brutos coletados, preparando-os para visualização e insights.

## 📝 Responsabilidades

- Recepção e processamento de fluxos de dados de temperatura.
- Transformação de dados brutos em formatos otimizados para leitura.
- Postagem de telemetria processada em filas de mensageria (RabbitMQ).
- Inicialização da infraestrutura de mensageria (Exchanges e Queues).

## 🔧 Detalhes Técnicos

- **Framework**: Spring Boot 3.4.0
- **Linguagem**: Java 21
- **Porta**: 8081
- **Mensageria**: RabbitMQ (Producer e Initializer)
- **Diferenciais**: Utiliza utilitários de UUIDv7 para compatibilidade adicional e ordenação temporal.

## 🚀 Execução

```bash
./gradlew bootRun
```

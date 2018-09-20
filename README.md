# adidas-coding-challenge

## Running
The easiest way to run the components is to use [docker-compose](https://docs.docker.com/compose/).
> NOTE: The first build might take a bit longer to deploy because the image layers need to be downloaded.

```bash
docker-compose up -d
```

### You can also run with `Makefile`

```bash
make run
```

## Usage

TODO


## CI/CD proposal

A CI/CD pipeline for a project of this type should look something like this for each step in the pipeline:

*****Test**
- Run all unit tests
- Lint code against a checkstyle predefined in the team
> If any of the above steps do not succeed, fail build
- Check code coverage and mark the code as healthy or unhealthy

**Build**
- Build docker images and tag versions

**Integration tests**
- Run contract testing
- Run integration testing in an environment as close as possible to production
> If any of the above steps do no succeed, fail build

**Deploy**
- Deploy to production with a possibility of a rollback to previous version if something goes wrong


## Improvements:

There are some improvements that should be made here:

- **Testing**: Include contract testing for the two services.
- **Persistence**: EhCache is used as a cache manager and an H2 as a database. The cache can be delegated to a Redis instance and not residing inside the application memory. This way, Redis can handle TTLs and serve as a cache store for both applications. H2 is only used for portability and convenience, it is not recommended for production as it cannot scale well. We should use any other kind of (No)SQL database.
- **Caching strategy**: Caching inside the `product-review` app is being emptied each hour and updated along with other CRUD operations. A more subtle strategy should be used, depending on how often the data might change and the importance of having the fresh data in real time.
- **Security**: Basic security is used but depending on our requirements, there is plenty of room to improve, like using OAuth2 instead. The username and password is also stored in the application properties or inside the configuration code. This and all other sensitive information should be set as environment variables and secrets to be injected upon deployment.
- **Error handling**: Errors are logged and error messages returned to clients however, we can also implement fallbacks, 'circuit breakers' and default ireturn cases to make our app more resilient.
- **Separation of concerns**: `product-review` app uses one POJO in all layers (Controller, Service, Repository), however, we should divide this into `ProductReviewEntity` and ProductReviewDto` so that the outer layer (Controller) only deals with our DTO object and the inner layers (Service and Repository) deal with our entity object. This way we can change either of them without breaking anything in either of the layers".
- And finally, we can always refactor and improve the code :)

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/es'
        headers {
        }
    }
    response {
        status 200
        body("Hola")
        headers {
            contentType(textPlain())
        }
    }
}
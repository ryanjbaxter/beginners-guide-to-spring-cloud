import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/de'
        headers {
        }
    }
    response {
        status 200
        body("Hallo")
        headers {
            contentType(textPlain())
        }
    }
}
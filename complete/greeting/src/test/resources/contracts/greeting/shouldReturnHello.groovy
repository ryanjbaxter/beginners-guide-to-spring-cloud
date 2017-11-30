package contracts.greeting

import org.springframework.cloud.contract.spec.Contract


Contract.make {
    request {
        method 'GET'
        url '/en'
        headers {
        }
    }
    response {
        status 200
        body("Hello")
        headers {
            contentType(textPlain())
        }
    }
}
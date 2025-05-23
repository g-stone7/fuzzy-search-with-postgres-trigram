package com.gstone.trigramdemo.api

import com.gstone.trigramdemo.service.CustomerDto
import com.gstone.trigramdemo.service.CustomerSearchService
import com.gstone.trigramdemo.service.PagedResult
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
@Validated
class CustomerController(private val customerSearchService: CustomerSearchService) {

    @GetMapping
    fun searchCustomers(
        @RequestParam("q") search: String,
        pageable: Pageable,
    ): PagedResult<CustomerDto> = customerSearchService.searchCustomers(search, pageable)

    @GetMapping("/specification")
    fun searchCustomersBySpecification(
        @RequestParam("q") search: String,
        pageable: Pageable,
    ): PagedResult<CustomerDto> =
        customerSearchService.searchCustomersBySpecification(search, pageable)

    @GetMapping("/native")
    fun searchCustomersByNativeQuery(
        @RequestParam("q") search: String,
        pageable: Pageable,
    ): PagedResult<CustomerDto> =
        customerSearchService.searchCustomersByNativeQuery(search, pageable)
}

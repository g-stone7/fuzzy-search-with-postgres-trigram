package com.gstone.trigramdemo.service

import com.gstone.trigramdemo.domain.CustomerRepository
import com.gstone.trigramdemo.mapping.asCustomerDto
import com.gstone.trigramdemo.mapping.asPagedResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerSearchService
@Autowired
constructor(
    private val customerRepository: CustomerRepository,
) {

    fun searchCustomers(search: String, pageable: Pageable): PagedResult<CustomerDto> {
        val result = customerRepository.searchCustomers(search, pageable)
        return result.asPagedResult(result.content.map { it.asCustomerDto() })
    }
}

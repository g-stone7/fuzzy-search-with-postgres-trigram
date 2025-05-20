package com.gstone.trigramdemo.service

import com.gstone.trigramdemo.domain.Address
import com.gstone.trigramdemo.domain.ContactDetails
import com.gstone.trigramdemo.domain.CustomFunctionsContributor.Companion.TRGM_WORD_SIMILARITY
import com.gstone.trigramdemo.domain.CustomerEntity
import com.gstone.trigramdemo.domain.CustomerRepository
import com.gstone.trigramdemo.mapping.asCustomerDto
import com.gstone.trigramdemo.mapping.asPagedResult
import jakarta.persistence.criteria.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

typealias Search = String

@Service
class CustomerSearchService
@Autowired
constructor(
    private val customerRepository: CustomerRepository,
) {

    fun searchCustomers(search: Search, pageable: Pageable): PagedResult<CustomerDto> {
        val result = customerRepository.findAll(search, pageable)
        return result.asPagedResult(result.content.map { it.asCustomerDto() })
    }

    fun searchCustomersBySpecification(
        search: Search,
        pageable: Pageable
    ): PagedResult<CustomerDto> {
        val result = customerRepository.findAll(search.toSpecification(), pageable)
        return result.asPagedResult(result.content.map { it.asCustomerDto() })
    }

    private fun Search.toSpecification(): Specification<CustomerEntity> {
        return Specification { root, _, criteriaBuilder ->
            val searchPredicates = mutableListOf<Predicate>()
            listOf(
                    root.get<Address>("address").get("street"),
                    root.get<Address>("address").get("city"),
                    root.get<ContactDetails>("contactDetails").get("firstName"),
                    root.get<ContactDetails>("contactDetails").get<String>("lastName"),
                )
                .forEach {
                    val searchExpression =
                        criteriaBuilder.function(
                            TRGM_WORD_SIMILARITY,
                            Boolean::class.java,
                            criteriaBuilder.literal(this),
                            it)
                    searchPredicates.add(criteriaBuilder.isTrue(searchExpression))
                }
            criteriaBuilder.or(*searchPredicates.toTypedArray())
        }
    }
}

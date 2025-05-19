package com.gstone.trigramdemo.domain

import java.util.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository :
    CrudRepository<CustomerEntity, UUID>, JpaSpecificationExecutor<CustomerEntity> {

    @Query(
        """
                    select c
                    from CustomerEntity c
                    where
                        trgm_word_similarity(:search, c.contactDetails.firstName)
                        OR trgm_word_similarity(:search, c.contactDetails.lastName)
                        OR trgm_word_similarity(:search, c.address.city)
                        OR trgm_word_similarity(:search, c.address.street)
                """)
    fun searchCustomers(@Param("search") search: String, pageable: Pageable): Page<CustomerEntity>
}

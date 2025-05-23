package com.gstone.trigramdemo.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.gstone.trigramdemo.domain.Address
import com.gstone.trigramdemo.domain.ContactDetails
import com.gstone.trigramdemo.domain.CustomerEntity
import com.gstone.trigramdemo.domain.CustomerRepository
import com.gstone.trigramdemo.service.CustomerDto
import com.gstone.trigramdemo.service.PagedResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CustomerControllerTest
@Autowired
constructor(
    private val mockMvc: MockMvc,
    private val customerRepository: CustomerRepository,
    private val objectMapper: ObjectMapper
) {

    @BeforeEach
    fun setUp() {
        customerRepository.deleteAll()
        customerRepository.save(buildCustomerEntity())
    }

    @ParameterizedTest
    @ValueSource(strings = ["Barcelna", "avenida", "de la c", "constitucon", "Jenifer"])
    fun `searchCustomers - should find customer when search query has typos`(search: String) {
        val result = hitSearch(get("/customers").queryParam("q", search))
        assertThat(result.totalElements).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Madrid", "John"])
    fun `searchCustomers - should not find customer when search query is not similar enough`(
        search: String
    ) {
        val result = hitSearch(get("/customers").queryParam("q", search))
        assertThat(result.totalElements).isEqualTo(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Barcelna", "avenida", "de la c", "constitucon", "Jenifer"])
    fun `searchCustomersBySpecification - should find customer when search query has typos`(
        search: String
    ) {
        val result = hitSearch(get("/customers/specification").queryParam("q", search))
        assertThat(result.totalElements).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Madrid", "John"])
    fun `searchCustomersBySpecification - should not find customer when search query is not similar enough`(
        search: String
    ) {
        val result = hitSearch(get("/customers/specification").queryParam("q", search))
        assertThat(result.totalElements).isEqualTo(0)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Barcelna", "avenida", "de la c", "constitucon", "Jenifer"])
    fun `searchCustomersByNativeQuery - should find customer when search query has typos`(
        search: String
    ) {
        val result = hitSearch(get("/customers/native").queryParam("q", search))
        assertThat(result.totalElements).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Madrid", "John"])
    fun `searchCustomersByNativeQuery - should not find customer when search query is not similar enough`(
        search: String
    ) {
        val result = hitSearch(get("/customers/native").queryParam("q", search))
        assertThat(result.totalElements).isEqualTo(0)
    }

    private fun buildCustomerEntity(): CustomerEntity =
        CustomerEntity(
            contractNumber = "CN123456",
            address =
                Address(
                    zipCode = "12345",
                    city = "Barcelona",
                    street = "Avenida de la Constitucion",
                    streetNumber = "12",
                ),
            contactDetails =
                ContactDetails(
                    firstName = "Jennifer",
                    lastName = "Doe",
                    email = "jane.doe@example.localhost",
                    phone = "1234567890"))

    private fun hitSearch(queryBuilder: MockHttpServletRequestBuilder): PagedResult<CustomerDto> {
        val result = mockMvc.perform(queryBuilder).andExpect(status().isOk).andReturn()
        return objectMapper.readValue(
            result.response.contentAsString, object : TypeReference<PagedResult<CustomerDto>>() {})
    }
}

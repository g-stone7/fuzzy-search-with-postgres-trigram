package com.gstone.trigramdemo.mapping

import com.gstone.trigramdemo.domain.Address
import com.gstone.trigramdemo.domain.ContactDetails
import com.gstone.trigramdemo.domain.CustomerEntity
import com.gstone.trigramdemo.service.ContactDetailsDto
import com.gstone.trigramdemo.service.CustomerDto
import com.gstone.trigramdemo.service.PagedResult
import org.springframework.data.domain.Page

fun <U, T> Page<U>.asPagedResult(content: List<T>): PagedResult<T> {
    return PagedResult(
        content = content,
        page = number,
        size = size,
        totalElements = totalElements,
        totalPages = totalPages)
}

fun CustomerEntity.asCustomerDto(): CustomerDto {
    return CustomerDto(
        id = id,
        contractNumber = contractNumber,
        address = address.asString(),
        contactDetails = contactDetails.asContactDetailsDto())
}

fun Address.asString(): String {
    return "$zipCode $city, $street $streetNumber"
}

fun ContactDetails.asContactDetailsDto(): ContactDetailsDto {
    return ContactDetailsDto(name = "$firstName $lastName", email = email, phone = phone)
}

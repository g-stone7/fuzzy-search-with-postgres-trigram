package com.gstone.trigramdemo.service

import java.util.UUID

data class CustomerDto(
    val id: UUID,
    val contractNumber: String,
    val address: String,
    val contactDetails: ContactDetailsDto,
)

data class ContactDetailsDto(val name: String, val email: String, val phone: String)

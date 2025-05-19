package com.gstone.trigramdemo.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "customer")
class CustomerEntity(
    @Id
    @Column(columnDefinition = "uuid", name = "id", unique = true, nullable = false)
    var id: UUID = UUID.randomUUID(),
    var contractNumber: String,
    @Embedded var address: Address,
    @Embedded var contactDetails: ContactDetails,
)

@Embeddable
class Address(
    @Column(name = "address_zip_code") var zipCode: String,
    @Column(name = "address_city") var city: String,
    @Column(name = "address_street") var street: String,
    @Column(name = "address_street_number") var streetNumber: String
)

@Embeddable
class ContactDetails(
    @Column(name = "contact_first_name") var firstName: String,
    @Column(name = "contact_last_name") var lastName: String,
    @Column(name = "contact_email") var email: String,
    @Column(name = "contact_phone") var phone: String,
)

package com.rinats.rin.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "role")
data class Role (
    @Id
    @Column(name = "role_id")
    val roleId: String,
    @Column(name = "role_name")
    val roleName: String,
)
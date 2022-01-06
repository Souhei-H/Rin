package com.rinats.rin.repository

import com.rinats.rin.model.compositeKey.TentativeShiftKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TentativeShiftRepository : JpaRepository<TentativeShift, TentativeShiftKey>
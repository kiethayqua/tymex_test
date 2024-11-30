package vn.kietnguyendev.tymexhometest.domain.usecase

import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(perPage: Int, since: Int) = userRepository.getUsers(perPage, since)
}
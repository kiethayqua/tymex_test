package vn.kietnguyendev.tymexhometest.domain.usecase

import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

class GetUserDetailUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String) = userRepository.getUserDetail(username)
}
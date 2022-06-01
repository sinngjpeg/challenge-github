package com.sinngjpeg.challengegithub.model

import java.io.Serializable

data class GithubRepository(
    val items: List<Item>
): Serializable

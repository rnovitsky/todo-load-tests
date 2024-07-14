package todo.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import todo.config.dto.Config

val gatlingConfig =
    ConfigLoaderBuilder.default()
        .addResourceSource("/configuration.yaml")
        .build()
        .loadConfigOrThrow<Config>()

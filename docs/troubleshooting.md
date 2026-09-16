# 环境 / 构建坑

> 按工作区 `AGENTS.md` §7 落点表：环境、构建、运行、工具链坑归本文；
> 已验证的 API 与领域事实见 [docs/reference/vanilla-crop-loot-tables.md](reference/vanilla-crop-loot-tables.md)。

## 构建环境

- 必须 Java 21：`export JAVA_HOME="C:/Users/lzp/scoop/apps/dragonwell21-jdk/current"`（Git Bash）。
- Gradle 9.2.1 wrapper + ModDevGradle 2.0.143，`./gradlew build` 产物 `build/libs/fortunecrops-1.0.0.jar`。

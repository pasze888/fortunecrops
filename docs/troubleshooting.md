# 环境 / 构建坑

> 按工作区 `AGENTS.md` §7 落点表：环境、构建、运行、工具链坑归本文；
> 已验证的 API 与领域事实见 [docs/reference/vanilla-crop-loot-tables.md](reference/vanilla-crop-loot-tables.md)。

## 构建环境

- 必须 Java 21：`export JAVA_HOME="C:/Users/lzp/scoop/apps/dragonwell21-jdk/current"`（Git Bash）。
- Gradle 9.2.1 wrapper + ModDevGradle 2.0.143，`./gradlew build` 产物 `build/libs/fortunecrops-1.0.0.jar`。

## CI 触发路径

`.github/workflows/build.yml` 的 push 触发带 `paths-ignore`：`**.md`、`docs/**`、`LICENSE*`、
`NOTICE`、`TEMPLATE_LICENSE.txt`、`logo.png`、`.gitignore`。只改这些路径的 push 不会触发
完整构建，省一次必然通过的构建。

- `pull_request` **刻意不加**路径过滤：被跳过的必需检查会停在 Pending，会卡住 PR 合并
  （GitHub 文档 "Skipping workflow runs"）。
- `.gitattributes` **刻意不排除**：它决定 `src/generated/**` 的换行，会影响产物。

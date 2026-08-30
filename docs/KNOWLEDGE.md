# KNOWLEDGE — fortunecrops

本文件记录本项目开发中**验证过**的事实（源码/原版数据核对），避免重复查证。

## 原版作物掉落表（1.21.1，从 `minecraft_1.21.1_client.jar` 提取核对）

- 路径为**单数**：`data/minecraft/loot_table/blocks/<block>.json`（1.20.5 起从 `loot_tables` 改名）。
  甜菜根的方块 id 是复数 `beetroots`，文件名 `data/minecraft/loot_table/blocks/beetroots.json`。
- **小麦 `wheat.json` / 甜菜根 `beetroots.json`**：pool 1 为 `alternatives`（成熟→产物 XOR 未成熟→1 种子，
  只执行其一），产物条目**无任何时运函数**，固定 1 个；pool 2 仅成熟时执行，给种子挂 `apply_bonus`。
- **胡萝卜 `carrots.json` / 土豆 `potatoes.json`**：pool 1 无条件 1 个产物；pool 2 成熟时再 1 个产物
  且直接挂 `apply_bonus`（食物本体=种子）。土豆另有 2% 毒马铃薯池。
- `apply_bonus` + `binomial_with_bonus_count` 参数：`extra=3`，`probability=0.5714286`（=4/7）。
  额外数量 ~ Binomial(3+时运等级, 4/7)，期望 = (3+等级)×4/7。
  成熟小麦种子：无时运期望 1+12/7≈2.71，时运 III 1+24/7≈4.43。
- 下界疣 `nether_wart.json`、甜浆果丛 `sweet_berry_bush.json`：本体直接吃时运
  （`apply_bonus` + `minecraft:uniform_bonus_count`，`bonusMultiplier: 1`）——
  "无独立种子的作物本体吃时运"的对照面。

## 本模组的实现要点

- **数据随 jar 分发即可覆盖原版**：mod jar 内 `data/minecraft/<命名空间>/...` 会覆盖原版同名数据，
  无需代码（NeoForge 把每个 mod 当作默认启用的数据包，加载顺序在 vanilla 之后）。
  完整文件覆盖 = 与其他覆盖同表的 mod/数据包互斥（后加载者胜），如需叠加需改用
  LootTable 加载事件在代码里插入函数。
- `javafml` 加载器要求 jar 必须有 `@Mod` 入口类，纯数据 mod 也要保留一个空主类。
- `random_sequence` 字段覆盖时保留原值（`minecraft:blocks/wheat` 等），指向原表自身的 RNG 序列。

## 构建环境

- 必须 Java 21：`export JAVA_HOME="C:/Users/lzp/scoop/apps/dragonwell21-jdk/current"`（Git Bash）。
- Gradle 9.2.1 wrapper + ModDevGradle 2.0.143，`./gradlew build` 产物 `build/libs/fortunecrops-1.0.0.jar`。

# Fortune Crops

让时运（Fortune）附魔作用于**小麦与甜菜根的作物本体**——与胡萝卜、土豆的待遇一致。

MC 1.21.1 / NeoForge 21.1.244。纯数据覆盖模组，**无任何游戏逻辑代码**。

## 机制

原版中，胡萝卜/土豆的"种子"就是食物本体，掉落表直接把 `apply_bonus` 时运函数挂在食物上；
而小麦/甜菜根的产物与种子分离，产物固定掉 1 个、不受时运。本模组把原版
`minecraft:blocks/wheat` 与 `minecraft:blocks/beetroots` 两张掉落表原样复制，仅在
产物条目上补上与胡萝卜完全相同的时运函数：

```json
{ "function": "minecraft:apply_bonus", "enchantment": "minecraft:fortune",
  "formula": "minecraft:binomial_with_bonus_count",
  "parameters": { "extra": 3, "probability": 0.5714286 } }
```

- 小麦本体：无时运期望 2.71 个（1–4），时运 III 期望 4.43 个；种子行为不变（本来吃时运）。
- 注意：这与胡萝卜完全同构，副作用是**无时运**的成熟小麦也不再固定掉 1 个。
  若想保留无时运时的原版基线（恰好 1 个），把两处 `extra: 3` 改为 `extra: 0` 即可。

## 实现方式

数据文件随 mod jar 分发：`src/main/resources/data/minecraft/loot_table/blocks/*.json`
（命名空间 `minecraft` 覆盖原版，NeoForge 将每个 mod 视为默认启用的数据包）。
主类仅为满足 `javafml` 加载器要求的空入口。

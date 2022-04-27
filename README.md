# LazyGrid
<p>
<a href="https://jitpack.io/#yusufarisoy/lazy-grid" rel="nofollow">
<img src="https://camo.githubusercontent.com/e4a2a059155953ea3cad876da5ca44bd417cc18d5cf997591951b44ced2d721d/68747470733a2f2f6a69747061636b2e696f2f762f6665767a696f6d757274656b696e2f506179566965772e737667" style="max-width: 100%;">
</a>
<img src="https://camo.githubusercontent.com/a05a54d005013841cf29ac57361136623697028285f4ecb2c153df14bd9fbf5d/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6d696e53646b56657273696f6e2d32312d79656c6c6f77677265656e" alt="Min SDK" data-canonical-src="https://img.shields.io/badge/minSdkVersion-21-yellowgreen" style="max-width: 100%;">
</p>
<p>LazyGrid library for [Jetpack Compose](https://developer.android.com/jetpack/compose).</p>

## Demo
### FillSize
All items of row shares the full width equally without any spaces.
| LazyGrid | LazyCollapsibleGrid |
| ------ | ------ |
| <img src="./demo/fill_size.gif" alt="gif" height="600" width="300"> | <img src="./demo/fill_size_collapsible.gif" alt="gif" height="600" width="300"> |

### FixedSize
Items placed with a fixed width and spaced equally according to remaining space.
| LazyGrid | LazyCollapsibleGrid |
| ------ | ------ |
| <img src="./demo/fixed_size.gif" alt="gif" height="600" width="300"> | <img src="./demo/fixed_size_collapsible.gif" alt="gif" height="600" width="300"> |

### SpacedBy
Items spaced by a fixed space and share the remaining width equally.
| LazyGrid | LazyCollapsibleGrid |
| ------ | ------ |
| <img src="./demo/spaced_by.gif" alt="gif" height="600" width="300"> | <img src="./demo/spaced_by_collapsible.gif" alt="gif" height="600" width="300"> |

## Setup
### Gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.yusufarisoy:lazy-grid:1.0.0'
}
```

### Compsable
```
LazyColumn(modifier = Modifier.fillMaxSize()) {
    LazyGrid(
        rows = listOf(),
        elementPerRow = 4,
        itemPlacementType = ItemPlacementType.FixedSize(itemWidth = 80.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { item, modifier ->
        CustomGridItem(item, modifier)
    }
    
    // Collapsible
    var isExpanded by remember { mutableStateOf(false) }
    
    LazyCollapsibleGrid(
        rows = listOf(),
        collapsibleRows = listOf(),
        elementPerRow = 3,
        itemPlacementType = ItemPlacementType.SpacedBy(space = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        isExpanded = isExpanded,
        enterTransition = expandVertically(),
        exitTransition = shrinkVertically(),
        collapseButton = { CustomCollapseButton(onClick = { isExpanded = !isExpanded }) }
    ) { item, modifier ->
        CustomGridItem(item, modifier)
    }
}
```
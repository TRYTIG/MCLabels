<h1 align="center">Labels</h1>

[![License: MIT](images/mit-badge.svg)](https://opensource.org/licenses/MIT)

Label your world with head textures, blocks and items or just plain old text.

![](images/head-example-campsite.gif "Campsite Example using Head texture")

## Commands
|Command|Description|Aliases|Permissions|
|--|--|--|--|
|``/labels``|Shows the about plugin message|``/labels about``|_none_|
|``/labels help``|Shows the plugin help message||_none_|
|``/labels reload``|Reloads the plugin configuration file||``labels.reload``|

## Configuration
See a simple configuration example [`here`](src/main/resources/config.yml).

## Installing
Simply copy the JAR file into your server plugins folder and restart.

## Requirements:
### Server requirements:
* Paper version 1.19 or greater
### Build requirements:
* JDK 17 (preferably Azul)
* Git

## Compiling
* Clone the repository
* Within the folder, run the following:
```shell
./gradlew build
```
Compiled JAR files will be in the `build/libs` directory

## License
Sit Plugin is licensed under the permissive MIT license. See [`LICENSE.txt`](LICENSE.txt) for more information.
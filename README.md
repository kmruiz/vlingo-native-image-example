# native-image-ping-pong

This is a sample application written using the vlingo.io platform. There are a few things
to consider before building:

1. [Requires a version of vlingo with this PR.](https://github.com/vlingo/vlingo-actors/pull/59)
2. [Requires a version of vlingo-build-plugin with this PR](https://github.com/vlingo/vlingo-build-plugins/pull/5)

## How To Build

It uses a multi-stage Dockerfile to build the native-image. Run the following command in your terminal:

```shell script
$> docker build -t vlingo/native-image .
```

And to run the example, run the following command:

```shell script
$> docker run -it vlingo/native-image vlingo-example
```

# Concurrence Project

Versión en español: [README](README.es.md)

## Overview

This project is a multi-module Maven application that demonstrates both sequential and concurrent approaches for processing data from the Disney API. It consists of two main modules:

- **disneyapi**: Provides functionality to fetch Disney character data and images.
- **excecutetest**: Contains test implementations to compare sequential and concurrent processing methods.

## Modules

### disneyapi
- Fetches character details from the [Disney API](https://api.disneyapi.dev).
- Downloads character images.
- Contains the domain model and mapping utilities.

### excecutetest
- Implements two strategies to calculate the total image size:
    - **Sequential**: Processes image downloads one by one.
    - **Concurrence**: Uses virtual threads for concurrent processing.
- Includes a simple time calculation utility to compare the performance of both approaches.

## Resultado
Virtual thread focusing is 10 times faster than sequential focusing.
![img.png](img.png)
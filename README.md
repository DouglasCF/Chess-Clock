# Chess-Clock

[![Build Development](https://github.com/DouglasCF/Chess-Clock/actions/workflows/pipeline-development.yml/badge.svg)](https://github.com/DouglasCF/Chess-Clock/actions/workflows/pipeline-development.yml)

This is an open source Chess Clock app for Android and iOS and it uses a KMM (Kotlin Multiplatform Mobile) as common codebase between the platforms.

## CI/CD

The project is structured to use Github Actions as CI/CD. There are 3 workflows:

- Feature branch: check tests and lint for the platforms.
- Development branch: generates a debug build for the platforms and upload them to the Github Releases page.
- Main branch: generates a release and signed build for the platforms and upload them to the Github Releases page.

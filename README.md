# Introduction
This repository is for a tre lambda that acts as the success destination of other tre lambdas

### Deployment

The `build-deploy-and-tag-ecr-image.yml` workflow in this repository builds and tags an image which is referred to in [da-tre-tf-module-common](https://github.com/nationalarchives/da-tre-tf-module-common/blob/main/lambda.tf#L51).

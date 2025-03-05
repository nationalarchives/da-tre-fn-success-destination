FROM  sbtscala/scala-sbt:eclipse-temurin-focal-17.0.5_8_1.9.4_2.12.18 AS builder
COPY . /lambda/src/
WORKDIR /lambda/src/
RUN sbt assembly

FROM public.ecr.aws/lambda/java:17
COPY --from=builder /lambda/src/target/da-tre-fn-success-destination.jar ${LAMBDA_TASK_ROOT}/lib/
CMD ["uk.gov.nationalarchives.tre.Lambda::handleRequest"]

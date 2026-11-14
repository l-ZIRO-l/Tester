# Phase 0 seed. Hardening flags live in run-sandbox.sh (single source of truth).
# Final image will use a jlink'd minimal JDK runtime (verified in Spikes S3/S4).
FROM eclipse-temurin:17-jdk-jammy
RUN useradd --create-home --uid 1000 runner
WORKDIR /sandbox
COPY --chown=runner:runner . /sandbox/
USER runner
ENTRYPOINT ["java", "-cp", "/sandbox", "Main"]

# Use the official PostgreSQL image as the base image
FROM postgres:latest

# Set environment variables for PostgreSQL
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD mario
ENV POSTGRES_DB jodb

# Expose the PostgreSQL port
EXPOSE 5432
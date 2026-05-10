# Deployment Guide

Spring Boot applications require a platform that supports running a Java Virtual Machine (JVM) backend and a SQL database (MySQL). **Vercel and Netlify are primarily designed for static sites and serverless frontends, and do not natively support Spring Boot Java applications.**

To deploy this website on a free or low-cost cloud platform, we recommend **Render** or **Railway**.

## Deploying on Render (Recommended)

1. **Create an account** on [Render.com](https://render.com/).
2. **Push your code to GitHub**, including the existing `Dockerfile` at the root of the project.
3. In the Render Dashboard, click **New +** -> **Web Service**.
4. Connect your GitHub repository.
5. Render will automatically detect the `Dockerfile` and select **Docker** as the environment.
6. Under **Advanced**, add the following Environment Variables:
   - `DB_URL`: The URL of your remote MySQL database (e.g., provided by PlanetScale or Railway).
   - `DB_USERNAME`: Database username.
   - `DB_PASSWORD`: Database password.
   - `MAIL_USERNAME`: (Optional) Your email for sending confirmations.
   - `MAIL_PASSWORD`: (Optional) Your email app password.
7. Click **Create Web Service**. Render will build the Docker container and deploy it, providing you with a live `.onrender.com` URL.

## Managing the Database

Since you will no longer be using `localhost:3306` in the cloud, you must provision a managed MySQL database:
1. **Railway** offers a quick MySQL database setup. You can copy the connection string and paste it into Render's `DB_URL` environment variable.
2. Alternatively, you can deploy the Spring Boot app **entirely on Railway** by importing the GitHub repo directly there, which seamlessly links the app with their MySQL addon.

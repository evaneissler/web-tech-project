# Stage 1: Build the application
FROM node:18-alpine AS build-stage

# Install dependencies
COPY package*.json ./   # Ensure you copy the package files from the correct directory
RUN npm install

# Copy source code
COPY ./

# Build the application for production
RUN npm run build

# Stage 2: Serve the app with Nginx
FROM nginx:stable-alpine AS production-stage

# Copy built assets from previous stage
COPY --from=build-stage /dist /usr/share/nginx/html  # Ensure correct path to 'dist'

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]


<template>
    <div class="form-container">
        <h2>Login Form</h2>
        <form action="#" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</template>

<script setup>
</script>

<style scoped>
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        .form-group label {
            flex: 1;
            margin-right: 10px;
        }
        .form-group input {
            flex: 2;
        }
        .form-container {
            display: flex;
            flex-direction: column;
            align-content: center;
            justify-content: center;
            align-items: center;
            width: 300px;
            margin: auto;
            background: red;
            padding: 10px;
        }
</style>
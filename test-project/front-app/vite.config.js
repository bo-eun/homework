import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    open: true,
    port: 4000,
    // 서버와의 통신을 위한 proxy 설정
    // 내부 경로에 /api로 시작하는 요청이 있을 경우 proxy로 경로를 바꿔줌
    // 서버와 클라이언트의 주소가 다른 경우 cros 에러를 방지하기 위해 사용
    // ex) http://localhost:4040/api/user -> http://localhost:9090/api/user 로 서버에 요청
    proxy: {
      "/api": {
        target: "http://localhost:9090/api",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});

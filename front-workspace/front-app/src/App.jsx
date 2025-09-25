import { useState } from 'react'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { RouterProvider } from 'react-router'
import { router } from './router/router'

// react query 설정
const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1, // 상대방 응답이 없을 경우 몇번 실행할지
      staleTime: 1 * 60 * 1000, // 최신 데이터 노출 지속 시간(데이터 신선한 시간...)
      gcTime: 1 * 60 * 1000, // 캐시 유지 시간
      // 다른 탭 갔다가 현재 탭으로 돌아와 포커스를 다시 받았을 때 데이터 다시 받기(새로고침 x)
      refetchOnWindowFocus: true,
    }
  }
})

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <RouterProvider router={router} />
      </QueryClientProvider>
    </>
  )
}

export default App

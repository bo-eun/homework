import { useState } from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { RouterProvider } from "react-router";
import { router } from "./router/router";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
      staleTime: 1 * 60 * 1000,
      gcTime: 1 * 60 * 1000,
      refetchOnWindowFocus: true,
    },
  },
});

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <QueryClientProvider client={queryClient}>
        <RouterProvider router={router} />
      </QueryClientProvider>
    </>
  );
}

export default App;

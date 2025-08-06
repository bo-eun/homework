import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import TodoList from "../exam/TodoList";

const user = userEvent.setup();

describe('투두 리스트 테스트', () => {
    let alertMock;

    // 시작하기 전에 사전 처리
    beforeEach(() => {
        alertMock = vi.spyOn(window, 'alert').mockImplementation(() => { });
    })

    afterEach(() => {
        alertMock.mockRestore();
    })

    it('첫 렌더링 시', () => {
        const { container } = render(<TodoList />);
        const row = container.querySelectorAll('td');
        expect(row).toHaveLength(0); // td가 하나도 없음
    })

    it('할 일 입력 안하고 추가버튼 클릭 시', async () => {
        render(<TodoList />);

        const input = screen.getByLabelText('할 일');
        const button = screen.getByRole('button', { name: '추가' });

        await user.type(input, ' ');
        expect(input.value.trim().length).toBe(0); //  할일 입력값 없음

        await user.click(button);
        expect(alertMock).toHaveBeenCalledWith('할일을 입력');

    })

    it('할 일 입력 후 추가버튼 클릭 시', async () => {
        const { container } = render(<TodoList />);

        const input = screen.getByLabelText('할 일');
        const button = screen.getByRole('button', { name: '추가' });

        await user.type(input, '공부하기');
        await user.click(button);

        const row = container.querySelectorAll('td');
        expect(row).toHaveLength(2);
        expect(screen.getByText('1')).toBeInTheDocument();
        expect(screen.getByText('공부하기')).toBeInTheDocument();
    })
})